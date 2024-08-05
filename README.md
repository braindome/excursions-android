# Excursions

## Background

As an intern at Polestar, I have learned a lot about Android Auto and Automotive OS, which are used in the car’s infotainment system. At the same time, I have been reading and learning more and more about Android development and dipped my toes in the Compose library for mobile phones. In this project, I wanted to put these two fields together and create an app which can be used both on a mobile phone and on a car, extending the phone capabilities to the larger car screen. So, with my classmate Tobias Sörensson, we came up with the idea of a Points of Interest app, where the user can pick a destination from a list of suggestions, defined by type and distance, and presented in a “swipe” format very common in dating apps.

## Purpose

The purpose of the project is to allow the product owner to present a curated list of interesting and relevant destinations for an excursion trip to the customer. The customer should primarily be able to choose between different categories of trips, save travel suggestions, and navigate to them both on their mobile device and the car's infotainment system.
Target Users

The target audience is the customers of the car manufacturer, who may want a spontaneous excursion outside the boredom of the city! Pre-set categories and suggestions help to quickly get started with traveling.
Project Management

I will be working solo on my GitHub repository, taking full responsibility for merging and branching. Small changes will be made directly on the main branch, while minor adjustments will be done in various feature branches. Testing of new features will occur on separate branches. Project management will involve both Confluence and Jira within a Kanban board framework. While there won't be sprints, regular check-ins with a mentor and classmates are planned. Planning, proposals, wireframing, and brainstorming will take place on Miro, while styling, flow, and specifications (fonts, colors, layouts) will be handled using Figma.

I will build a native Android app in Kotlin using Jetpack Compose as the framework, following the MVVM (Model-View-ViewModel) architecture. Location data will be retrieved through the Google Places API, leveraging its SDK for simplified handling of asynchronous operations. Firebase Auth and Firestore will be utilized for storing user profiles, settings, and data. Later in the project, a local database will be implemented to store saved destinations and places. The choice between Room and Realm will be considered. Support for Android Auto is also planned for later phases of the project.

### Requirements for MVP 1.0

- Navigation between different views using the navigation bar and the "back button.”
- Profile pages: five or six predefined categories that should be editable (edit button) (part of the navigation).
- Display results for categories and allow the user to click on right or left arrows.
- Save "liked" places in the favorites view.
- Use only a placeholder image for the places.
- Temporarily fetch places from the Places API into an array.
- Ability to open Google Maps from favorites -> category -> place -> Navigation.
- Online only (no local database in MVP1).
- Ability to edit categories
- Ability to change distance only, nothing else.
- Only the mobile app for MVP 1.0, no Android Auto or CarPlay.

### Requirements for MVP 2.0
- Store settings and destinations on Google Firebase Firestore.
- Auto/CarPlay support.
- Destination pictures/gallery.
- Auth (Firebase).

## Tech Stack
- Android Studio
- Kotlin
- Firebase Firestore
- Firebas Auth
- Google Places API/SDK
- Retrofit
- Jetpack Compose

## Getting Started
In the project root, create the `secrets.properties` file. Edit it and add the following variable:
```
API_KEY=<Your API Key>
```
Run the app in the Android Studio emulator or on your phone.




