
import pages.GroupPage;
import pages.GroupsPage;
import selenium_helpers.GroupsSubcategory;

final class GroupCreator {
    private final String description;
    private final String groupName;
    private final boolean restriction;
    private final GroupsSubcategory groupsSubcategory;
    private final GroupsPage groupsPage;

    private GroupCreator(Builder builder) {
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

        GroupCreator build() {
            return new GroupCreator(this);
        }
    }
}
