# Progress Bar Visualizer

A dynamic progress bar desktop application built with **Java** and **Swing**, counting from **0% to 100%** in real time (1% per step). Built as a CV portfolio project to demonstrate Java GUI development skills.

---

## Preview

The application opens a desktop window with an animated progress bar, live percentage counter, elapsed timer, and speed controls.

---

## Features

- Start — begins loading from 0% to 100%
- Pause — pauses at the current percentage
- Resume — continues from where it paused
- Reset — resets everything back to 0%
- Speed control — Slow / Normal / Fast
- Color interpolation — bar color transitions from blue to indigo to green
- Shimmer and glow effect — custom painted bar with visual effects
- Elapsed timer — shows how long the loading has been running

---

## Technologies

| Technology | Usage |
|------------|-------|
| Java 21 | Core programming language |
| Java Swing | GUI framework (JFrame, JPanel, JButton, JLabel) |
| Java AWT | Graphics2D, Color, GradientPaint, RadialGradientPaint |
| javax.swing.Timer | Animation loop (counts 1% per step) |
| IntelliJ IDEA | IDE used for development |

---

## Package Structure

```
src/
├── Main.java                          # Entry point — starts the application
└── com/progressbar/
    ├── ui/
    │   ├── AppFrame.java              # Main window — builds the UI
    │   └── BarPanel.java              # Custom painted progress bar
    ├── controller/
    │   └── TimerController.java       # Timer logic — controls animation
    └── config/
        └── AppColors.java             # Color constants and interpolation
```

---

## How to Run (IntelliJ IDEA)

### Prerequisites
- Java JDK 17 or higher installed
- IntelliJ IDEA (Community Edition is free)

### Steps

1. Clone the repository:
```bash
git clone https://github.com/Simeon-Petrov/ProgressBarVisualizer.git
```

2. Open IntelliJ IDEA -> File -> Open -> select the project folder

3. Make sure the SDK is set to Java 17+:
   - File -> Project Structure -> Project SDK

4. Open `src/Main.java`

5. Press **Shift + F10** or click the green Run button to start

The application window will open automatically.

---

## Architecture

The project follows **Separation of Concerns** — each class has a single responsibility:

- `Main` — only starts the application
- `AppFrame` — only builds and manages the UI components
- `BarPanel` — only handles the custom drawing of the bar
- `TimerController` — only handles the animation timing and logic
- `AppColors` — only stores color constants and the interpolation utility

---

## License

This project is open source and available for portfolio and educational use.

---

## Author

Simeon Petrov
