# Project 3 - *Twitter*

**Twitter** is an android app that allows a user to view his Twitter timeline and post a new tweet. The app utilizes [Twitter REST API](https://dev.twitter.com/rest/public).

Time spent: **15** hours spent in total

## User Stories

The following **required** functionality is completed:

* [x]	User can **sign in to Twitter** using OAuth login
* [x]	User can **view tweets from their home timeline**
* [x] User is displayed the username, name, and body for each tweet
* [x] User is displayed the [relative timestamp](https://gist.github.com/nesquena/f786232f5ef72f6e10a7) for each tweet "8m", "7h"
* [x] User can **compose and post a new tweet**
* [x] User can click a “Compose” icon in the Action Bar on the top right
* [x] User can then enter a new tweet and post this to twitter
* [x] User is taken back to home timeline with **new tweet visible** in timeline
* [x] Newly created tweet should be manually inserted into the timeline and not rely on a full refresh

The following **stretch** features are implemented:

* [x] User can **see a counter with total number of characters left for tweet** on compose tweet page
* [x] User can **pull down to refresh tweets timeline**
* [x] User is using **"Twitter branded" colors and styles**
* [ ] User sees an **indeterminate progress indicator** when any background or network task is happening
* [ ] User can **select "reply" from detail view to respond to a tweet**
* [ ] User that wrote the original tweet is **automatically "@" replied in compose**
* [ ] User can tap a tweet to **open a detailed tweet view**
* [ ] User can **take favorite (and unfavorite) or reweet** actions on a tweet
* [x] User can **see embedded image media within a tweet** on list or detail view.

The following **bonus** features are implemented:

* [ ] User can view more tweets as they scroll with infinite pagination
* [ ] Compose tweet functionality is build using modal overlay
* [x] User can **click a link within a tweet body** on tweet details view. The click will launch the web browser with relevant page opened.
* [x] Use Parcelable instead of Serializable using the popular [Parceler library](http://guides.codepath.org/android/Using-Parceler).
* [ ] Replace all icon drawables and other static image assets with [vector drawables](http://guides.codepath.org/android/Drawables#vector-drawables) where appropriate.
* [ ] User can view following / followers list through any profile they view.
* [ ] Use the popular ButterKnife annotation library to reduce view boilerplate.
* [ ] On the Twitter timeline, leverage the [CoordinatorLayout](http://guides.codepath.org/android/Handling-Scrolls-with-CoordinatorLayout#responding-to-scroll-events) to apply scrolling behavior that [hides / shows the toolbar](http://guides.codepath.org/android/Using-the-App-ToolBar#reacting-to-scroll).
* [ ] User can **open the twitter app offline and see last loaded tweets**. Persisted in SQLite tweets are refreshed on every application launch. While "live data" is displayed when app can get it from Twitter API, it is also saved for use in offline mode.


The following **additional** features are implemented:
* [x] Tweets display number of favorites and retweets (with Twitter icons)
* [x] Replaced some icon drawables and other static image assets with [vector drawables]
* [x] Added a Floating Action Button to compose a new Tweet instead of using Action Bar/Menu


## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src='https://github.com/SBPin/Twitter/blob/master/simpleTweet.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />

GIF created with [LiceCap](http://www.cockos.com/licecap/).

## Notes

I initially had many difficulties with layouts and XML. However, as this project required many different TextViews, buttons, images, etc., I leanred much more about properly constructing layouts.

I also struggled with retrieving other parts of the Tweet via JSON. However, after reading Twitter's documentation regarding the Tweet object, I was able to learn more about retrieving values from JSON, and figured out on my own how to retrieve values for the number of Retweets and Favorites.

Also, after returning to work on July 5th, I had some difficulty remembering where I was in the project, and my code was not compiling. However, as I've learned to commit to GIT more frequently, I was able to clone the most recent, running version. This experience was initially frustrating, but helped teach me the value of frequent commits.

Lastly, I had trouble inserting and using the Floating Action Button. We were instructed to use an Action Bar/Menu for users to compose a new Tweet, but I decided to use a FAB instead, as we has used Action Bars before, and I wanted to try something new. This was initially difficult, as my peers were not implementing the same feature, and I had to read and understand the documentation on my own. This ended up being worthwile, as with the help of the TAs and some time spent on StackOverflow, I was able to implement the feature and learn on my own. As I used a FAB, many of the Stretch/Bonus features involving Tool Bar/ Action Bar were not applicable.

## Open-source libraries used

- [Android Async HTTP](https://github.com/loopj/android-async-http) - Simple asynchronous HTTP requests with JSON parsing
- [Glide](https://github.com/bumptech/glide) - Image loading and caching library for Android

## License

    Copyright [2019] [Sabrina Pin]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
