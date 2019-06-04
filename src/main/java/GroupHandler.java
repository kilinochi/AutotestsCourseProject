
import pages.GroupPage;
import pages.GroupsPage;
import selenium_helpers.GroupsSubcategory;

final class GroupHandler {
    private final String description;
    private final String groupName;
    private final boolean restriction;
    private final GroupsSubcategory groupsSubcategory;
    private final GroupsPage groupsPage;

    private GroupHandler(Builder builder) {
        this.groupsPage = builder.groupsPage;
        this.description = builder.description;
        this.groupName = builder.groupName;
        this.restriction = builder.restriction;
        this.groupsSubcategory = builder.groupsSubcategory;
    }

    GroupPage create() {
        final GroupsPage.SelectGroupsDialogAlert selectCreatorGroupsCard
                = groupsPage.clickToSelectGroupDialogAlert();
        final GroupsPage.SelectGroupPageTypeDialogAlert selectCreatorGroupPageTypeCard
                = selectCreatorGroupsCard.getGroupPageList().get(0);
        final GroupsPage.ModalDialogAlert modalDialogAlert
                = selectCreatorGroupPageTypeCard.clickToModalDialogAlert();
        modalDialogAlert.inputName(groupName);
        modalDialogAlert.inputDescription(description);
        modalDialogAlert.selectSubcategory(groupsSubcategory);
        modalDialogAlert.selectRestriction(restriction);
        return modalDialogAlert.clickToCreateGroupButton();
    }

    GroupsPage deleteAllGroups() {
        final GroupsPage.OwnerSideBar ownerSideBar
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

        GroupHandler build() {
            return new GroupHandler(this);
        }
    }
}
