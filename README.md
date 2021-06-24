# Appcent-Task
# News App 
News application using the API from https://newsapi.org/
API News Is JSON API for live news and blog articles from the media.
News Feed is a simple  that gives you latest and best news from multiple sources so you no longer need to swap between apps to stay informed.

# Architecture
I choose to use MVVM (Model-View-ViewModel) as my project architecture in order to provide a cleaner code, with clear separation between the view, the data and the business logic.
The following diagram shows all the modules and how each module interact with one another after. This architecture using a layered software architecture.!
![alt text](https://user-images.githubusercontent.com/55722619/81968739-a8bec700-95d1-11ea-8682-48fe879c25ff.png)

# Built With 🛠
* [Kotlin](https://kotlinlang.org/) - official programming language for Android development .
* [RxJava](https://github.com/ReactiveX/RxJava) - for asynchronous programming .
* [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Collection of libraries that help you design robust, testable, and maintainable apps.
  - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - Data objects that notify views when the underlying database changes.
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on UI changes. 
  - [Navigation](https://developer.android.com/guide/navigation) - 
* [Retrofit](https://square.github.io/retrofit/) - A type-safe HTTP client for Android and Java.
* [Fragment](https://developer.android.com/guide/components/fragments)
* [Glide](https://bumptech.github.io/glide/) for image loading
* [Data Binding Library](https://www.google.com/search?q=databinding&oq=databinding&aqs=chrome.0.0j69i59j0l2j69i60j69i65j69i60l2.1381j0j7&sourceid=chrome&ie=UTF-8) for image loading
