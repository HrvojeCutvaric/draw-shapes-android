# Draw Shapes (KMP)

A Kotlin Multiplatform (Compose Multiplatform) demo app for drawing basic shapes on a canvas.
The user selects a shape from the bottom bar, then places it using two taps:
1) Center point
2) Reference point (depends on the shape)

## Demo
<img src="https://github.com/user-attachments/assets/d5b304fa-dc97-474c-b5b3-01ae7b0b78e9" width="320" height="640" />

## How it works
- Select a shape (Circle / Square / Triangle)
- Tap once to set the **center**
- Tap again to set the **reference point**:
  - **Circle:** second point is on the circumference (distance = radius)
  - **Square:** second point is a corner (center-to-corner = half of the diagonal)
  - **Triangle:** second point is a vertex (center-to-vertex = circumradius)

## Run the project

### Android
Open the project in Android Studio and run the `composeApp` configuration.

### iOS
1. Open the iOS project in Xcode (usually `iosApp/`).
2. Select an iOS simulator.
3. Run.
