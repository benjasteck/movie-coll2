package easv.dk.BE;

import java.sql.Date;

public class Movie{
        private final String title;
        private double userRating;
        private final double imdbRating;
        private final String movieUrl;
        private final Date lastView;
        private final int Id;
        private String category;

        public Movie(String title, double userRating, double imdbRating, Date lastView, String movieUrl, int Id) {
                this.title = title;
                this.userRating = userRating;
                this.imdbRating = imdbRating;
                this.lastView = lastView;
                this.movieUrl = movieUrl;
                this.Id = Id;
        }


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

        public Date getLastView() {
                return lastView;
        }

        public String getMovieUrl() {
                return movieUrl;
        }

        public void setUserRating(int userRating) {
                this.userRating = userRating;
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
