# Block_shooting_game

A 2D block shooting game implemented in Java as part of an Object-Oriented Programming course.  
The project focuses on clean OOP design, real-time game loop execution, collision detection, and event-driven architecture.

---

## 🎮 Game Overview
The game consists of a paddle controlled by the keyboard, a moving ball, and multiple blocks.
The player aims to destroy all blocks by bouncing the ball using the paddle while tracking the score.

---

## 🧠 Key Concepts & Design
- Real-time **game loop** with fixed frame rate
- **Collision detection** between ball, blocks, and paddle
- **Object-Oriented Design** using interfaces and polymorphism
- **Event/listener mechanism** for hit detection and score updates
- Clear separation between:
  - Game logic
  - Rendering
  - User input

---

## 🧱 Project Structure
- `Game` – manages the main game loop and rendering
- `Sprite` (interface) – represents any drawable and updatable object
- `Ball`, `Block`, `Paddle` – core game objects
- `SpriteCollection` – manages all sprites in the game
- `HitListener` & `ScoreTrackingListener` – event-driven hit handling

---

## 🛠 Technologies Used
- **Java**
- **Object-Oriented Programming**
- **Ant** (build & run automation)
- **biuoop** GUI library
- **Git & GitHub** for version control

---

## ⚙️ Build & Run
The project uses **Apache Ant** for compilation and execution.

```bash
ant run

## 📸 Screenshots

Below are example screenshots of the game during runtime.
<img width="1203" height="946" alt="צילום מסך 2025-12-28 232849" src="https://github.com/user-attachments/assets/2bd2aecd-5a07-422b-9fb0-d4dd5db5e32b" />
<img width="1199" height="945" alt="צילום מסך 2025-12-28 233033" src="https://github.com/user-attachments/assets/6a29e431-60d8-4bd7-ae6a-711d4ce944b5" />
<img width="1223" height="945" alt="צילום מסך 2025-12-28 233222" src="https://github.com/user-attachments/assets/a608d1b3-0519-4bd8-baf8-dc519172e405" />

<img width="1223" height="945" alt="image" src="https://github.com/user-attachments/assets/9e147ea3-a1ba-4ff4-84af-58d46023174e" />

