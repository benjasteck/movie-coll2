package easv.dk.BE;

public class Movie{
        private String title;
        private double userRating;
        private double imdbRating;
        private String movieUrl;
        private String lastView;
        private int Id;
        private String category;

        public Movie(String title, double userRating, double imdbRating, String lastView, String movieUrl, int Id) {
                this.title = title;
                this.userRating = userRating;
                this.imdbRating = imdbRating;
                this.lastView = lastView;
                this.movieUrl = movieUrl;
                this.Id = Id;
        }

        //public Movie(String spiderMan2, String s, String s1, String s2, String movieUrl) {
      //  }

        public int getId() {
                return Id;
        }

        public String getTitle() {
                return title;
        }

        public double getUserRating() {return userRating;}

        public double getImdbRating() {
                return imdbRating;
        }

        public String getLastView() {
                return lastView;
        }

        public String getMovieUrl() {
                return movieUrl;
        }

        public void setTitle(String title) {
                this.title = title;
        }

        public void setUserRating(int userRating) {
                this.userRating = userRating;
        }

        public void setImdbRating(int imdbRating) {
                this.imdbRating = imdbRating;
        }

        public void setMovieUrl(String movieUrl) {
                this. movieUrl =  movieUrl;
        }

        public void setLastView(String lastView) {
                this.lastView = lastView;
        }

        public String getCategory() {return category;}

        @Override
        public String toString() {
                return title;
        }

        public void setCategory(String category) {
                this.category = category;
        }
}
