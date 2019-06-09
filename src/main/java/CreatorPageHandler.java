
import pages.group_page.GroupPage;
import pages.groups_page.GroupsPage;
import pages.groups_page.side_bars.GroupsSideBar;
import selenium_helpers.GroupsSubcategory;

final class CreatorPageHandler {

    private final String description;
    private final String groupName;
    private final boolean restriction;
    private final GroupsSubcategory groupsSubcategory;
    private final GroupsPage groupsPage;

    private CreatorPageHandler(Builder builder) {
        this.groupsPage = builder.groupsPage;
        this.description = builder.description;
        this.groupName = builder.groupName;
        this.restriction = builder.restriction;
        this.groupsSubcategory = builder.groupsSubcategory;
    }

    GroupPage create() {
        return groupsPage
                .clickToSelectGroupDialogAlert()
                .getGroupPageList().get(0)
                .clickToModalDialogAlert()
                .inputName(groupName)
                .inputDescription(description)
                .selectSubcategory(groupsSubcategory)
                .selectRestriction(restriction)
                .clickToCreateGroupButton();
    }

    GroupsPage deleteAllGroups() {
        final GroupsSideBar groupsSideBar
                = groupsPage.getOwnerSideBar();
        do  {
            groupsSideBar
                    .clickToMineGroupsSelector()
                    .get(0)
                    .clickToGroup()
                    .deleteGroup();
        }
        while (groupsSideBar.isVisible());
        return groupsPage;
    }

    static final class Builder {

        private final GroupsPage groupsPage;
        private String description;
        private String groupName;
        private boolean restriction;
        private GroupsSubcategory groupsSubcategory;

        Builder(final GroupsPage groupsPage) {
            this.groupsPage = groupsPage;
        }

        Builder isRestriction(final boolean isRestriction) {
            this.restriction = isRestriction;
            return this;
        }

        Builder inputName(final String name) {
            this.groupName = name;
            return this;
        }

        Builder inputDescription(final String description) {
            this.description = description;
            return this;
        }

        Builder category(final GroupsSubcategory subcategory) {
            this.groupsSubcategory = subcategory;
            return this;
        }

        CreatorPageHandler build() {
            return new CreatorPageHandler(this);
        }
    }
}
