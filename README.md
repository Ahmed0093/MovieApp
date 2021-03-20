# MovieApp
Learning Repo Movie Android App

This project is aimed for learning and to get hands dirty with MVVM architecture using Kotlin, Navigation Component,LiveData,Coroutines with adding Unit Tests and UI test using Espresso.

lazy functianlites and deafult value constractor were used from kotlin to make an easy dependency injection without using external dependency injection framework.

## Getting Started
## Installation
Clone this repository and import into **Android Studio**
```bash
git clone git@github.com:Ahmed0093/MovieApp.git
```
## Generating APK
From Android Studio:
1. ***Build*** menu
2. Build Bundle(s)/APK
3. Build APK


## Run UnitTest
```bash
gradlew unittest
```
## Run UI Test
```bash
gradlew connectedAndroidTest
```

## The app contains 2 screen

1- list called from Cache Json File in raw folder 
  user can search the movies and the result is categorized by year for each top 5 per year.

2-the details screen 
  - Movie Title , Year ,  Genres and Cast 
  - two column flicker images .

## Contributing

1. Fork it
2. Create your feature branch (git checkout -b my-new-feature)
3. Commit your changes (git commit -m 'Add some feature')
4. Push your branch (git push origin my-new-feature)
5. Create a new Pull Request


### TO DO
 
-Adding More Scripts From GithubAction


## Acknowledgments

	
