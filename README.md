Hackspace Management System (HMS)
=================================

A Java implementation of the [Hackspace Management System (HMS)](https://github.com/NottingHack/hms) used by Nottingham Hackspace

Project Goals
-------------
- Produce a parity-release of the existing PHP based HMS system in Java
- Schema compatible with the existing HMS database, requiring zero schema changes
- Make use of Spring Boot and the Spring Framework to provide a standalone executable web application
- Make it easy for developers to get up and running, it should be as simple as `git clone hms-java` followed by `./mnvw package` then `./hms-java.jar`
- Provide Continuous Integration via TravisCI

Project Status
--------------

Most of the basic functionality has been implemented but this is still very much work in progress. A lot of the core
infrastructure is now in place which will make a lot of the remaining tasks straightforward.

Working:

- Login
- List/View Members (also email history, rfid cards)
- List Groups
- Snackspace / Transactions List
- List/View Tools
- Security

Missing and In Progress:

- Membership business logic and workflow (in progress)
- The ability to edit anything, most things only have the "view" or "list" implemented
- Integration with 3rd party service such as Google Calendar and Mailchimp
- Bank statement importing
- Member Voice module
- Test coverage (Unit, Integration, Functional)

Blockers:

- Adding users and changing passwords via Kerberos, will likely need to shell out to `kadmin`

Developer Notes
---------------

This section is quite rough at the moment, it may end up moving to the Github wiki pages for the project.

Thymeleaf conditional (th:if="..."):

TODO: document the various utils we've used in the templates #temporal, #lists etc.

\#lists - Utility class for performing list operations.
EG: ${#lists.isEmpty(tools)}
return true if the list 'tools' is empty

\#temporals - Thymeleaf Dialect to format and create Java 8 Time objects
see: https://github.com/thymeleaf/thymeleaf-extras-java8time
EG: ${#temporals.format(weekDay, 'E, dd/MM/yyyy')}
formats the weekDay property (java.time.LocalDate) as 'Sun, 27/12/2015'

> Note that the th:if attribute will not only evaluate boolean conditions. Its capabilities go a little beyond that, and it will evaluate the specified expression as true following these rules:
>
>    If value is not null:
>        If value is a boolean and is true.
>        If value is a number and is non-zero
>        If value is a character and is non-zero
>        If value is a String and is not “false”, “off” or “no”
>        If value is not a boolean, a number, a character or a String.
>    (If value is null, th:if will evaluate to false).


In ThymeLeaf templates a [SpringMVC utility](http://docs.spring.io/spring/docs/current/spring-framework-reference/html/mvc.html#mvc-links-to-controllers-from-views)
is exposed via the *#mvc* expression which is useful for building links to controllers and actions.

`${#mvc.url('MC#status').arg(0, '1').build()}`

The controller name *MC* in this case refers to the MemberController and must be unique. If another controller with the
same name exists such as *MonkeyController* then an `IllegalArgumentException` will be thrown when rendering the view
with the message `No unique match for mapping mappingName`. In this case your can specify a unique name in using the 
`@RequestMapping` annotation, for example `@RequestMapping(name = "MCT")`.

Null-safe traversal
SpEL can make use of '?' as a null safe operator

This example:
th:if="${member.account.paymentReference}"
Would normally result in:
Exception evaluating SpringEL expression: "member.account.paymentReference"
which is due to member.account being null, to resolve this you can use the '?' operator:
th:if="${member.account?.paymentReference}"

When accessing fields from a model in a Thymeleaf template the method name is not required as the template engine can
rely on "Bean" naming conventions to locate the property. For example, `${member.email}` will be translated into
``member.getEmail()`. This is also the case with custom methods that are in no way related to fields

Controller Redirect
return a  a view name with a prefix of "redirect:" to redirect to that controller method.

Lambda Expressions

`long totalMembers = memberStatusCounts.stream().mapToLong(MemberStatusCount::getCount).sum();`

is equivalent to:

```
long totalMembers = 0;
for (MemberStatusCount memberStatusCount : memberStatusCounts) {
  totalMembers += memberStatusCount.getCount();
}
```

Spring Data JPA uses a query derivation mechanism which might seem like black magic at times.

http://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repository-query-keywords
http://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods.details

There are some additional keywords related to limiting the result set:
http://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.limit-query-result

Spring Security

A completely different approach has be taken compared to HMS PHP, groups still exist but are no longer used for
permission checking, they exist as a way to group permissions together. A group has a set of associated permissions
which can be altered at any time.

For example, in HMS PHP there was a permission check to see if a member is able to view the "admin features" on a
member, this involved checking if the currently logged in member was in either the "full access", "membership admin" or
"membership" groups and these conditions were hard coded into the controller. In HMS Java a new permission called
VIEW_MEMBER_ADMIN_FEATURES has been added which restricts access to the admin features. It's simply a case of assigning
this permission to the relevant groups.

Apart from the "static permissions" there are also some dynamic permissions in place. These permissions are not based on
a permission from the permission table, these include:
- view/edit self (in most cases you are allowed full read/write permissions on your own things such as profile,
tool bookings, transaction list).
- tools (tools you've been inducted on or are a maintainer for)

@Secured and @PreAuthorize are equivalent except @PreAuthorize allows SpEL (Spring Expression Language) expressions as
conditions where as @Secured relies on Granted Authorities. They both intercept calls to methods to and prevent access
if the outcome evaluates to false. HMS Java makes use of @PreAuthorize rather than @Secured to allow more control.

@Secured("ROLE_USER")
@PreAuthorize("hasRole('USER')")

The annotations can also be applied at the class level EG:

    @PreAuthorize("hasAuthority('" + Permission.VIEW_TOOLS + "')")
    public class ToolController
    ...

In this case, the @PreAuthorize condition will be used for any methods which do not supply their own annotation. Method
level @PreAuthorize annotations will always override the class level one. //TODO: does this cause issues with private methods?

authentication - represents the token for an authentication request or for an authenticated principal once the request
has been processed by the AuthenticationManager. This can be used to find out how the user is authenticated or to obtain
the principal.

principal - the logged in user which will typically be member, this means it's normally safe to cast the principal
object to a Member. In some cases the principal can be a String, for example when running a background task from within
the application but these are edge cases.

Thymeleaf and the Permission class
http://stackoverflow.com/questions/27756169/concatenate-string-and-variable-in-secauthorize-thymeleaf-tag/31257127#31257127

<th:block sec:authorize="hasAuthority(T(com.example.app.Permission).VIEW_USER_EMAIL)">
   ...
</th:block>

Validation Constants

You will notice that certain entities have a set of constants near the top of their class such as:

    public static final int USERNAME_MAX_LENGTH = 50;

This is used to define the validation constraints on the field. In a lot of cases the database schema will dictate the
longest value that can be stored in the field. This may be due to the data type used for the database column or it may
have been designed around external constrains of other systems (eg: a maximum size accepted by Kerberos, the underlying
authentication system). In other cases it may just be a sensible limit has been decided.

The constant can be referenced in the field definition:

    @Column(name = "username", length = USERNAME_MAX_LENGTH)
    @Size(max = USERNAME_MAX_LENGTH)
    private String username;

Here we can see that the @Column length has been set which is used by Hibernate when it generates the database schema
(DDL). We can also see the @Size annotation which is used when checking that the values in a submitted form are correct.

The constant can also be referenced directly from the view template to enforce a maximum length on a field:

    <input type="text" id="username" th:field="*{username}" th:maxlength="${T(uk.org.nottinghack.domain.Member).USERNAME_MAX_LENGTH}"/>

Remember that although the field has a maxlength defined, this is only a client side check, there is nothing stopping
this from being bypassed apart from the web browser, in this case the other validation mechanisms will kick in.

AspectJ Compile Time Weaving

What is it?

The AspectJ aspects are woven into the application classes during compile time by the `aspectj-maven-plugin`.

WARNING: Some IDEs such as IntelliJ will attempt to weave the aspects when the application is compiled via the IDE
rather than Maven, this seems to be a bit hit and miss as I've experienced some of the AspectJ aspects not being woven
into the application classes.

Deliberate Changes

The `SnackspaceController` was renamed to `TransactionController` to reflect the fact it no longer only used for Snackspace transactions but tool charges and payment now. The implmentation is pretty much the same as HMS-PHP.

The `EmailRecordsController` has been removed and the functionality has been included in the `MemberController` as it makes more sense in the context of a member. The feature which allowed a user to view every email HMS has ever sent was removed as it's not very useful and very slow.

Improvements over HMS-PHP

- Very easy to deploy, it's just "./hms-php.jar"
- Very easy to build, it's just "./mnvw package"
- Database compatible, requires no database schema changes.
- Significant reduction in queries hitting the database due to better fetch-control.
- Access control is now based on 'permissions' rather tha groups, this means that permissions can be assigned at runtime rather than relying on hard coded group access logic.
- The tool calendar is now far more flexible, it supports bookings of any length based on 15 minute intervals including spanning multiple days.
- Several date navigation issues fixed on the tool calendar.
- Continious Integration via TravisCI - builds the project and runs all tests upon a git push


System Requirements
- Java 8 Runtime (JRE)

Development Requirements
- Java 8 Development Kit (JDK)
- Apache Maven (build tool)

Libraries Used
- Spring Boot
- Spring Framework
- Spring Data JPA
- Spring Security
- QueryDSL
- Hibernate ORM
- Apache Tomcat
- Thymeleaf Template Engine