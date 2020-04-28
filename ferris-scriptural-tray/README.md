# ferris-scriptural-tray

This is a Java stand-alone scripture alert application which uses the windows
system tray for alerting.  The binary of this project
can be downloaded, unziped, configured, and run with no additional setup
needed (it even comes with it's own JRE so not even Java needs to be installed
on the server). 

See <https://mjremijan.github.io/ferris-scriptural> for more details.

# Java 8 and JavaFX Note.

This project use Java 8 JDK. Getting a Java 8 JDK is pretty easy. But just in case,
at the root of this project is a Zulu Java 8 JDK binary for Windows. 

This project also uses JavaFX. At the time, Oracle stopped supporting JavaFX and
it was taken up by the open source community. I used OpenJFX 8 for this project. 
Unfortunately, getting OpenJFX 8 may prove a challenge. I thankfully found 
[openjfx-win](https://github.com/scoop-software/openjfx-win) on GitHub. I put
the OpenJFX 8 binary for Windows at the root of this project. All you need
to do is unzip it in your Java 8 home directory and you should be good to go.

> NOTE
> Both of these are for a **development** environment. The releases come
> bundled with a JRE that includes JavaFX.

# Production Deployment Procedures

1. Commit all files.
1. Run parent "Update Versions", enter the production version `mvn versions:set`
1. Commit all POM files.
1. Create TAG for the next version.
1. Run parent "Update Versions" to update all POM files to the next SNAPSHOT
1. Commit all POM files.
1. Push all (including TAG) to GitHub
1. Checkout the TAG
1. Run parent "Clean and Verify"
1. Run parent "Site"
1. Run this "Assembly"
1. Run parent "Site Deploy" 
1. Create GitHub release
1. Upload this *.zip and *.tar.gz files to the GitHub release


