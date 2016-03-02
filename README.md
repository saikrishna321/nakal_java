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
	    <version>f4dcb0054e</version>
	</dependency>
```

## Usage

Environment Varaibles should hold  
  * PLATFORM=ios/android
  * MASKIMAGE=image which needs to be considered for masking

For Example:

```
NakalExecutor nakalExecutor = new NakalExecutor();
     //Compare mobile native app screen
     @Test
     public void compareImagesExecutor(){
         Assert.assertTrue(nakalExecutor.nakalExecutorNativeCompare("ActivityScreen"));
     }
     //Compare mobile-web/desktop-browser app screen
     @Test
     public void compareImagesExecutor(){
         Assert.assertTrue(nakalExecutor.nakalExecutorWebCompare(driver, "GoogleScreen"));
      }
```


Now, execute your test by passing env variable NAKAL_MODE=build to build the baseline images. All baseline images will be stored in baseline_images folder in current directory

once baseline is built, next execution onwards, start using environment variable NAKAL_MODE=compare to compare against baseline.
any difference will be put in the same directory with image file named "difference_current_screen_name.png"

All image masks should and be stored at /ios/mask-images/fileName.png image mask should be saved.(mask images with transparent background has been created using gimp tool- eg:https://github.com/saikrishna321/nakal_java/tree/master/android/mask_images)
MASKIMAGE value in the env should be the same fileName which is stored under the mask_images.

#Running the tests

<h1>Comparing images on native applications</h1>

PLATFORM="android" APP="native" NAKAL_MODE="build" MASKIMAGE="oneplus" mvn clean -Dtest=AndroidTest test (captures a baseline image)

PLATFORM="android" APP="native" NAKAL_MODE="compare" MASKIMAGE="oneplus" mvn clean -Dtest=AndroidTest test ( compares expected and actual image)

<h1>Comparing images on web application</h1>
For Appium,Selendroid(PLATFORM=android/ios) and WebDriver(PLATFORM=Desktop)

PLATFORM="Desktop" APP="web" NAKAL_MODE="build" mvn clean -Dtest=WebDriverTest test (captures a baseline image)

PLATFORM="Desktop" APP="web" NAKAL_MODE="compare" mvn clean -Dtest=WebDriverTest test ( compares expected and actual image)


<h2>Ruby Client</h2>
	https://github.com/rajdeepv/nakal


## Contributing

1. Fork it ( http://github.com/<my-github-username>/nakal_java/fork )
2. Create your feature branch (`git checkout -b my-new-feature`)
3. Commit your changes (`git commit -am 'Add some feature'`)
4. Push to the branch (`git push origin my-new-feature`)
5. Create new Pull Request






