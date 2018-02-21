<h1 align="center">
	<br>
	<img width="600" src="https://github.com/saikrishna321/nakal_java/blob/master/Nakal.png" alt="nakal">
	<br>
	<br>
	<br>
</h1>

Automated visual testing of Android/iOS/Web applications.

![ScreenShot](https://raw.githubusercontent.com/saikrishna321/nakal_java/master/difference_HomeScreen1.png)

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

Following mandatory properties need to be set before running test:  
  * nakal.platform=ios/android
  * nakal.maskimage=image which needs to be considered for masking
  * nakal.mode=build/compare

For Example:

```
NakalExecutor nakalExecutor = new NakalExecutor();
     //Compare mobile native app screen
     @Test
     public void compareImagesExecutor(){
         Assert.assertTrue(nakalExecutor.nakalExecutorNativeCompare("HomeScreen"));
     }
     //Compare mobile-web/desktop-browser app screen
     @Test
     public void compareImagesExecutor(){
         Assert.assertTrue(nakalExecutor.nakalExecutorWebCompare(driver, "GoogleScreen"));
      }
```


1. Now, execute your test by passing env variable NAKAL_MODE=build to build the baseline images. All baseline images will be stored in baseline_images folder in current directory

2. Once baseline is built, next execution onwards, start using environment variable NAKAL_MODE=compare to compare against baseline.
any difference will be put in the same directory with image file named "difference_current_screen_name.png"

All mask images should and be stored at /ios/mask-images/fileName.png /android/mask-images/fileName.png.(mask images with transparent background has been created using gimp tool- eg:https://github.com/saikrishna321/nakal_java/tree/master/android/mask_images)
MASKIMAGE value in the env should be the same fileName which is stored under the mask_images(For ex: ./mask_images/nexus5.png and MASKIMAGE="nexus5").

#Running the tests

mvn clean -Dtest=AndroidTest test -Dnakal.platform=android -Dnakal.mode=build -Dnakal.maskimage=nexus5 (captures a baseline image)

mvn clean -Dtest=AndroidTest test -Dnakal.platform=android -Dnakal.mode=compare -Dnakal.maskimage=nexus5 ( compares expected and actual image)

## Ignore certain regions of the image

1. Create nakal.yaml under the root directory.
2. You can specify the areas of a screen you want to mask/ignore while comparing in nakal.yaml as below

```
nexus5:
  HomeScreen: {mask_region_1: [69, 441, 357, 553],mask_region_2: [50, 1600, 371, 1652]}
  SearchScreen: {mask_region_1: [66,424,340,478],mask_region_2: [76,524,440,578]}
```
3.If you want to set certain threshold while comparing. You can pass option as:

```
nakalExecutor.nakalExecutorNativeCompare("HomeScreen",3)
```


<h2>Ruby Client</h2>
	https://github.com/rajdeepv/nakal


## Contributing

1. Fork it ( http://github.com/<my-github-username>/nakal_java/fork )
2. Create your feature branch (`git checkout -b my-new-feature`)
3. Commit your changes (`git commit -am 'Add some feature'`)
4. Push to the branch (`git push origin my-new-feature`)
5. Create new Pull Request






