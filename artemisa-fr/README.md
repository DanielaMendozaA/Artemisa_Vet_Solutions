# Artemisa

## Description

This project, **Artemisa**, is a web veterinary software developed with React, Vite, and various libraries for state management, form handling, and UI components. The project includes MUI for UI design, Redux Toolkit for state management, and other useful tools such as `axios` for making HTTP requests and `dayjs` for handling dates.

## Dependencies

This project requires the following dependencies:

| Dependency                 | Version    | Description                                                            |
|----------------------------|------------|------------------------------------------------------------------------|
| `@emotion/react`           | ^11.13.3   | Library for styling components in React with CSS-in-JS.                |
| `@emotion/styled`          | ^11.13.0   | API for creating styled components using Emotion.                      |
| `@hookform/resolvers`      | ^3.9.0     | Resolvers for integrating validation with `react-hook-form`.           |
| `@mui/icons-material`      | ^6.1.1     | Material Design icons for MUI.                                         |
| `@mui/material`            | ^6.1.0     | MUI UI components.                                                     |
| `@mui/styled-engine`       | ^6.1.0     | Styling engine used by MUI.                                            |
| `@mui/x-date-pickers`      | ^7.17.0    | Date picker components from MUI.                                       |
| `@reduxjs/toolkit`         | ^2.2.7     | Official toolkit for state management with Redux.                      |
| `axios`                    | ^1.7.7     | Promise-based HTTP client for the browser and Node.js.                 |
| `dayjs`                    | ^1.11.13   | Lightweight date and time library similar to Moment.js.                |
| `jwt-decode`               | ^4.0.0     | Library for decoding JSON Web Tokens (JWT).                            |
| `lucide-react`             | ^0.441.0   | Customizable SVG icons for React.                                      |
| `mui`                      | ^0.0.1     | Internal or project-specific dependency.                               |
| `notistack`                | ^3.0.1     | Notification system for Material-UI.                                   |
| `react`                    | ^18.3.1    | Library for building user interfaces.                                  |
| `react-dom`                | ^18.3.1    | Provides DOM-specific methods for React applications.                  |
| `react-hook-form`          | ^7.53.0    | Library for managing forms in React.                                   |
| `react-phone-input-2`      | ^2.15.1    | Phone number input component for React.                                |
| `react-redux`              | ^9.1.2     | Official React bindings for Redux.                                     |
| `react-router-dom`         | ^6.26.2    | DOM bindings for React Router, a library for routing in React apps.    |
| `yup`                      | ^1.4.0     | JavaScript schema validation library.                                  |

## Development Dependencies

This project also includes the following development dependencies:

| Dependency                      | Version   | Description                                                            |
|---------------------------------|-----------|------------------------------------------------------------------------|
| `@eslint/js`                    | ^9.9.0    | ESLint with support for modern JavaScript syntax.                      |
| `@types/axios`                  | ^0.14.0   | TypeScript definitions for `axios`.                                    |
| `@types/react`                  | ^18.3.3   | TypeScript definitions for React.                                      |
| `@types/react-dom`              | ^18.3.0   | TypeScript definitions for `react-dom`.                                |
| `@vitejs/plugin-react-swc`      | ^3.5.0    | Vite plugin to compile React using SWC.                                |
| `eslint`                        | ^9.9.0    | Linter tool for identifying and reporting on patterns in JavaScript.   |
| `eslint-plugin-react-hooks`     | ^5.1.0-rc.0 | ESLint rules for React hooks.                                         |
| `eslint-plugin-react-refresh`   | ^0.4.9    | ESLint plugin to enable React Refresh.                                 |
| `globals`                       | ^15.9.0   | Global variables predefined for ESLint.                                |
| `typescript`                    | ^5.5.3    | Superset of JavaScript that adds static types.                         |
| `typescript-eslint`             | ^8.0.1    | TypeScript integration for ESLint.                                     |
| `vite`                          | ^5.4.1    | Fast build tool for modern web development.                            |

## Scripts

The following scripts are defined in the project:

| Script        | Description                                                                                      |
|---------------|--------------------------------------------------------------------------------------------------|
| `dev`         | Runs the development server using Vite.                                                          |
| `dev:watch`   | Runs the development server with Nodemon to watch for changes.                                   |
| `build`       | Compiles TypeScript and builds the project using Vite.                                           |
| `lint`        | Lints the project files using ESLint.                                                            |
| `preview`     | Previews the production build using Vite.                                                        |

## Setup and Installation

1. Clone the repository:

    ```bash
    git clone https://github.com/ArtemisaVetSolution/Artemisa-fr.git
    cd artemisa
    ```

2. Install the dependencies:

    ```bash
    npm install
    ```

3. Start the development server:

    ```bash
    npm run dev
    ```

4. To build the project for production:

    ```bash
    npm run build
    ```

5. To preview the production build:

    ```bash
    npm run preview
    ```

