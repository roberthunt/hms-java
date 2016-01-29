package uk.org.nottinghack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import uk.org.nottinghack.service.GroupService;
import uk.org.nottinghack.service.PermissionService;

/**
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
@Controller
@RequestMapping("/groups")
public class GroupController
{
    private static final String TEMPLATE_DIR = "group/";
    private static final String LIST_GROUPS = "list";
    private static final String VIEW_GROUP = "view";
    private static final String EDIT_GROUP = "edit";

    private final GroupService groupService;
    private final PermissionService permissionService;

    @Autowired
    public GroupController(GroupService groupService, PermissionService permissionService)
    {
        this.groupService = groupService;
        this.permissionService = permissionService;
    }

    @RequestMapping
    public String index(Model model)
    {
        model.addAttribute("groups", groupService.getGroups());
        model.addAttribute("permissions", permissionService.getPermissions());
        return TEMPLATE_DIR + LIST_GROUPS;
    }

    @RequestMapping("/{groupId}")
    public String view(@PathVariable int groupId, Model model)
    {
        return TEMPLATE_DIR + VIEW_GROUP;
    }

    @RequestMapping("/{groupId}/edit")
    public String edit(@PathVariable int groupId, Model model)
    {
        return TEMPLATE_DIR + EDIT_GROUP;
    }
}
