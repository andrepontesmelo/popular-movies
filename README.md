# Popular Movies

This is an Android app that fetches and displays popular movie information,
reviews and ratings using themoviedb.org REST api.

## Capabilities
 - User can sort movies by popularity or rating
 - Tablet layout optimization applied automatically depending on the device
 - Local sqlite database being used in order to cache movie data
 and reduce REST api calls
 - User can play movie trailers
 - User can share movie trailer url

## Libraries currently being used
  - [ **Picasso** ]( https://github.com/square/picasso ) for image
handling/caching
  - [ **BoD** ]( https://github.com/BoD/android-contentprovider-generator )
to generate the a sqlite content provider automatically

## Screenshots

### Small screen / phones

![Alt text](https://raw.githubusercontent.com/andrepontesmelo/popular-movies/screenshots/screenshots/list_small_screen.png?raw=true "Movie list view for small screen.")

![Alt text](https://raw.githubusercontent.com/andrepontesmelo/popular-movies/screenshots/screenshots/details_small_screen.png "Movie details view for small screen.")


![Alt text](https://raw.githubusercontent.com/andrepontesmelo/popular-movies/screenshots/screenshots/settings.png "App settings")


### Tablet optimized layout

![Alt text](https://raw.githubusercontent.com/andrepontesmelo/popular-movies/screenshots/screenshots/tablet_optimized.png "Tablet screen")
