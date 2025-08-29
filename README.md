# Simple Event App

A modular, clean architecture Android application for managing events with secure login/logout, local encrypted storage, and reactive UI updates.

---

## 🚀 Features

### Authentication
- ✅ Login functionality with session persistence using **DataStore**.
- ✅ Logout feature with confirmation dialog (`BottomSheetDialogFragment`).
- ✅ Automatic navigation based on login state.

### Event Management
- ✅ Display list of events and upcoming event.
- ✅ Add new event with detailed form in a `BottomSheetDialogFragment`.
- ✅ Upload event thumbnail using modern image picker APIs.
- ✅ Smooth UI updates with `RecyclerView` and `DiffUtil`.

### Architecture & Design
- ✅ **Clean Architecture**:
  - Clear separation between presentation, domain, and data layers.
  - Use cases encapsulate business logic.
  - Mappers for DTO and domain models.
- ✅ **Modularized App** with separate modules for core, presentation, common utilities.
- ✅ Reactive programming with **Kotlin Flow** for state management and data observation.
- ✅ Dependency injection using **Koin**.

### Persistence & Security
- ✅ Local data storage with **Room Database**.
- ✅ Encrypted database using **SQLCipher**.
- ✅ Secure login state management using **DataStore Preferences**.

### UI/UX
- ✅ Material Design components (`MaterialButton`, `MaterialCardView`).
- ✅ Responsive layouts with `ConstraintLayout` and `CoordinatorLayout`.
- ✅ Bottom sheet dialogs for event creation and logout confirmation.
- ✅ Thumbnail loading and caching with **Glide**.

### Tools & Libraries
- ✅ Leak detection integrated using **LeakCanary**.
- ✅ Kotlin Coroutines and Lifecycle-aware components.
- ✅ Retrofit for network requests (authentication).

---

## 📦 Technology Stack

| Technology       | Usage                         |
|------------------|-------------------------------|
| Kotlin           | Programming language          |
| Android Jetpack  | ViewModel, LiveData, DataStore|
| Room + SQLCipher | Local encrypted database      |
| Koin             | Dependency Injection          |
| Kotlin Flow      | Reactive data streams         |
| Glide            | Image loading and caching     |
| Material Design  | UI components                 |
| LeakCanary       | Memory leak detection         |
| Retrofit         | Network calls (auth)          |

---

## 📋 Usage

1. Clone this repository
2. Build and run the app on your Android device or emulator
3. Use the login screen to authenticate
4. View, add, and manage events securely with encrypted local storage
5. Logout with session cleared

---

## 🛠️ Future Improvements

- Add unit and instrumentation tests for ViewModels and UseCases.
- Implement offline-first sync with remote API.
- Enhance UI/UX with animations and accessibility improvements.

---

## 💬 Contributions

Feel free to open issues or submit pull requests!

---

## 📄 License

[MIT License](LICENSE)

---

*Happy coding!* 🚀


## 🗂️ Project Structure
- app/ # Application module
- core/ # Business logic, data layer, database, use cases
- presentation/ # UI layer: Fragments, ViewModels, Adapters
- common/ # Shared utilities, base classes, extensions
