# Nakal_Java

Automated visual testing of Android/iOS/Web applications.

Nakal is used to add visual validations in your existing test framework (using appium or selenium-webDriver etc).

## Installation
You need to install [imagemagick](http://www.imagemagick.org/script/index.php) on your machine

Add this to your pom.xml 
```
    <repositories>
        <repository>
            <id>jitpack.io</id>
            <url>https://jitpack.io</url>
        </repository>
    </repositories>
    
<dependency>
	    <groupId>com.github.saikrishna321</groupId>
	    <artifactId>nakal_java</artifactId>
	    <version>d6f95906ef</version>
	</dependency>
```

## Usage

Environment Varaibles should hold  
  * PLATFORM=ios/android
  * MASKIMAGE=image which needs to be considered for masking

For Example:

```
NakalExecutor nakalExecutor = new NakalExecutor();

    @Test
    public void captureScreenShotFromDevice(){
        nakalExecutor.nakalExecutorCompareScreenAndCreateDiffImage("current_screen_name");
    }

```


Now, execute your test by passing env variable NAKAL_MODE=build to build the baseline images. All baseline images will be stored in baseline_images folder in current directory

once baseline is built, next execution onwards, start using environment variable NAKAL_MODE=compare to compare against baseline.
any difference will be put in the same directory with image file named "difference_current_screen_name.png"

All image masks should and be stored at /ios/mask-images/fileName.png image mask should be saved.(mask images with transparent background has been created using gimp tool- eg:https://github.com/saikrishna321/nakal_java/tree/master/android/mask_images)
MASKIMAGE value in the env should be the same fileName which is stored under the mask_images.

#Running the tests

PLATFORM="android" NAKAL_MODE="build" MASKIMAGE="oneplus" mvn clean -Dtest=AndroidTest test (captures a baseline image)

PLATFORM="android" NAKAL_MODE="compare" MASKIMAGE="oneplus" mvn clean -Dtest=AndroidTest test ( compares expected and actual image)

Ruby Client::https://github.com/rajdeepv/nakal

<h2>WIP</h2>
  Handling WebScreenshot capture with Webdriver and Appium


## Contributing

1. Fork it ( http://github.com/<my-github-username>/nakal_java/fork )
2. Create your feature branch (`git checkout -b my-new-feature`)
3. Commit your changes (`git commit -am 'Add some feature'`)
4. Push to the branch (`git push origin my-new-feature`)
5. Create new Pull Request






