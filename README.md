# Satellite Information App

https://github.com/user-attachments/assets/8a029ac2-7f70-46b3-964a-d186ea849343

This app provides users with the ability to view satellite information. It allows access to satellite details and real-time tracking of satellite positions. Users can observe the active or inactive status of satellites, select a specific satellite for detailed information, and filter the list by searching for specific satellites.

## Features

- **Splash Screen**: Displays a short animation when launching the app.
- **Onboarding Screen**: A three-step tutorial introducing the app’s features.
  - **Step 1**: Quick access to the satellite list.
  - **Step 2**: Access to satellite details.
  - **Step 3**: Tracking satellite positions.
- **Satellite List Screen**: Displays a list of all satellites with active/inactive status indicators.
- **Search Feature**: Users can filter satellites by name in the list.
- **Satellite Detail Screen**: Shows detailed information about a selected satellite.

## Screenshots

| Screen | Image |
| ------ | ----- |
| Splash | <img src="https://github.com/user-attachments/assets/22d146f8-5cf2-4b99-b81c-e18076d10a71" width="200" height="400" /> | 
| Onboarding - 1 | <img src="https://github.com/user-attachments/assets/cdd98bb0-ceae-422d-b962-e4133f8247c0" width="200" height="400" /> |
| Onboarding - 2 | <img src="https://github.com/user-attachments/assets/047b28ce-a748-47c6-8af7-de22e52dbe1b" width="200" height="400" /> |
| Onboarding - 3 | <img src="https://github.com/user-attachments/assets/2b356ece-0232-42a0-890b-644d1e06004c" width="200" height="400" /> |
| Satellite List | <img src="https://github.com/user-attachments/assets/3822c1bf-64b9-43b1-a061-1819806d9df5" width="200" height="400" /> |
| Search Feature | <img src="https://github.com/user-attachments/assets/754d552e-8071-4735-8c73-34ba11538104" width="200" height="400" /> |
| Satellite Detail | <img src="https://github.com/user-attachments/assets/e211dc07-ea64-4c22-9558-a2fe7ed9593c" width="200" height="400" /> |

## Technologies and Libraries Used

- **Kotlin**: Primary programming language.
- **Jetpack Hilt**: Used for dependency injection (DI).
- **Navigation Component**: Manages navigation between screens.
- **View Binding**: Provides safe and easy access to XML components.
- **Lottie**: Displays animations in the splash screen.
- **Room**: Used for local data storage of satellite details.
- **StateFlow**: Manages UI state within the application.

## Architecture

The project follows Clean Architecture principles and has a modular structure. Each feature is organized as an independent module:

- **App Modules**:
  -  `app`: Serves as the entry point of the application and contains the application's configuration.

- **Data Modules**:
  - `data`: Contains data sources and repository structures.
  - `datasource`: Includes classes related to remote and local data sources.
  - `mapper`: Contains mapper classes that convert data models to UI models.
  - `repository`: Provides data and interacts with ViewModels through repository classes.
  - `room`: Contains Room structures for local database management.

- **Domain Modules**:
  - `domain`: Holds use cases and interfaces.
  - `model`: Contains models used in the UI.
  - `usecase`: Houses the necessary use cases for each business logic (e.g., fetching, searching, filtering).
  
- **Feature Modules**:
  - `onboarding`: Contains the onboarding screens that introduce the app to the user.
  - `satellites`: Contains the satellite list and search functionality.
  - `satellitedetail`: Contains the satellite detail screen.
  
- **Core Modules**:
  - `common`: Contains general-purpose utility classes, extension functions, and shared structures for the app.
 
- **Navigation Modules**:
  - `eyojnavigation`: Contains structures for managing navigation operations.

### Clean Architecture Layers

1. **Data Layer**: Manages API services, database operations, and data sources.
2. **Domain Layer**: Contains business logic and Use Cases.
3. **UI Layer**: Holds ViewModels and UI components.

## Installation and Running the App

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/username/project-name.git
   cd project-name
