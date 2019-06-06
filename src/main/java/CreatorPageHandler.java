
import pages.group_page.GroupPage;
import pages.groups_page.GroupsPage;
import pages.groups_page.side_bars.OwnerSideBar;
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
        final OwnerSideBar ownerSideBar
                = groupsPage.getOwnerSideBar();
        while (ownerSideBar.isVisible()) {
            ownerSideBar
                    .clickToMineGroupsSelector()
                    .get(0)
                    .clickToGroup()
                    .deleteGroup();
        }
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

        Builder isRestriction(boolean isRestriction) {
            this.restriction = isRestriction;
            return this;
        }

        Builder inputName(String name){
            this.groupName = name;
            return this;
        }

        Builder inputDescription(String description) {
            this.description = description;
            return this;
        }

        Builder category(GroupsSubcategory subcategory) {
            this.groupsSubcategory = subcategory;
            return this;
        }

        CreatorPageHandler build() {
            return new CreatorPageHandler(this);
        }
    }
}
