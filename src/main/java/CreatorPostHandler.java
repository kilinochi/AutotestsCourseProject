import pages.GroupPage;

final class CreatorPostHandler {

    private final GroupPage groupPage;
    private final String musicSearchText;
    private final int rangeMusic;
    private final int musicFrom;
    private final String textInPost;

    private CreatorPostHandler(final Builder builder) {
        groupPage = builder.groupPage;
        musicSearchText = builder.musicTextSearch;
        rangeMusic = builder.countMusic;
        musicFrom = builder.musicFrom;
        textInPost = builder.textInPost;
    }

    GroupPage.GroupTopicsTab createMusicPost() {
        final GroupPage.PostDialogAlert postDialogAlert = groupPage.clickToCreatePostArea();
        postDialogAlert.inputText(textInPost);
        postDialogAlert.searchAndAttachMusic(musicSearchText, musicFrom, rangeMusic);
        return postDialogAlert.clickToCreatePostButton();
    }

    static class Builder {

        private String musicTextSearch;
        private int musicFrom;
        private int countMusic;
        private String textInPost;

        private final GroupPage groupPage;

        Builder(final GroupPage groupPage) {
            this.groupPage = groupPage;
        }

        Builder fromRange(int musicFrom, int countMusic) {
            this.musicFrom = musicFrom;
            this.countMusic = countMusic;
            return this;
        }

        Builder searchAndAttachMusic(String musicTextSearch) {
            this.musicTextSearch = musicTextSearch;
            return this;
        }

        Builder inputText(String textInPost) {
            this.textInPost = textInPost;
            return this;
        }

        CreatorPostHandler build() {
            return new CreatorPostHandler(this);
        }
    }
}
