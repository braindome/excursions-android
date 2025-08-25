# Excursions

## Background

As an intern at Polestar, I have learned a lot about Android Auto and Automotive OS, which are used in car infotainment systems. At the same time, I have been expanding my knowledge of Android development and experimenting with the Jetpack Compose library for mobile phones. In this project, I wanted to combine these two fields and create an app that can be used both on a mobile phone and in a car, extending phone capabilities to the larger car screen. Together with my classmate Tobias Sörensson, we came up with the idea of a Points of Interest app, where users can pick a destination from a list of suggestions, defined by type and distance, and presented in a “swipe” format commonly seen in dating apps.

## Purpose

The purpose of the project is to allow the product owner to present a curated list of interesting and relevant destinations for an excursion trip to the customer. The customer should primarily be able to choose between different categories of trips, save travel suggestions, and navigate to them both on their mobile device and the car's infotainment system.

### Target Users

The target audience is customers of the car manufacturer who may want a spontaneous excursion outside the city. Pre-set categories and suggestions help users quickly get started with traveling.

### Project Management

I am working solo on my GitHub repository, taking full responsibility for merging and branching. Small changes are made directly on the main branch, while minor adjustments are done in various feature branches. Testing of new features occurs on separate branches. Project management involves both Confluence and Jira within a Kanban board framework. While there are no sprints, regular check-ins with a mentor and classmates are planned. Planning, proposals, wireframing, and brainstorming take place on Miro, while styling, flow, and specifications (fonts, colors, layouts) are handled using Figma.

I am building a native Android app in Kotlin using Jetpack Compose as the framework, following the MVVM (Model-View-ViewModel) architecture. Location data is retrieved through the Google Places API, leveraging its SDK for simplified handling of asynchronous operations. Firebase Auth and Firestore are used for storing user profiles, settings, and data. Later in the project, a local database will be implemented to store saved destinations and places, with a choice between Room and Realm. Support for Android Auto is also planned for later phases of the project.

**Note:** The codebase is currently being refactored to use callbacks more extensively. This work is in progress.

## MVP 1.0 Requirements

- Navigation between different views using the navigation bar and the back button.
- Profile pages: five or six predefined categories that should be editable (edit button) (part of the navigation).
- Display results for categories and allow the user to click on right or left arrows.
- Save "liked" places in the favorites view.
- Use only a placeholder image for the places.
- Temporarily fetch places from the Places API into an array.
- Ability to open Google Maps from favorites → category → place → navigation.
- Online only (no local database in MVP 1.0).
- Ability to edit categories.
- Ability to change distance only, nothing else.
- Only the mobile app for MVP 1.0, no Android Auto or CarPlay.

## MVP 2.0 Requirements

- Store settings and destinations on Google Firebase Firestore.
- Auto/CarPlay support.
- Destination pictures/gallery.
- Authentication (Firebase).

## Tech Stack
- Android Studio
- Kotlin
- Firebase Firestore
- Firebas Auth
- Google Places API/SDK
- Retrofit
- Jetpack Compose

## Prerequisites
- Android Studio (2025.1.2 or later)
- JDK 17+
- Minimum SDK: 26

## Installation
1. Clone the repository:
    ```
    git clone https://github.com/yourusername/excursions.git
    cd excursions
    ```
2. Follow the steps in **Getting Started**.

## Getting Started
1. In the project root, create a `secrets.properties` file.
2. Edit it and add the following variable:
    ```
    API_KEY=<Your API Key>
    ```
3. Run the app in the Android Studio emulator or on your phone.




