# Stream-Event

Native Android Application developed in Kotlin.
Using the following stack:
- MVVM
- ViewModelScope - Coroutines
- View-binding
- Retrofit
- Hilt

For app functionality please [document here](./Android_Assessment.pdf)

- app icon credit source: [Live streaming icons created by Flat Icons - Flaticon](https://www.flaticon.com/free-icons/live-streaming)

## Dev of App
- mainly done on Pixel 5 emulator running api 28 and Huawei device running API 30
- android studio Android Studio Chipmunk | 2021.2.1 Patch 2
  Build #AI-212.5712.43.2112.8815526, built on July 10, 2022
  Runtime version: 11.0.12+0-b1504.28-7817840 aarch64
  VM: OpenJDK 64-Bit Server VM by JetBrains s.r.o.
  macOS 12.1
  GC: G1 Young Generation, G1 Old Generation
  Memory: 2048M
 
## UI tests not running
- left the code there to show my thinking see [EventFragmentTest](app/src/androidTest/java/com/example/streamevent/view/ScheduleFragmentTest.kt).
- main struggle was with hilt
