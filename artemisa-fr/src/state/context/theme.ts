import { createTheme } from "@mui/material/styles";
// import { extendTheme as joyExtendTheme } from '@mui/joy/styles';

// Define the structure for the theme options including color schemes
declare module "@mui/material/styles" {
  interface Theme {
    colorSchemes: {
      light: ThemeOptions;
      dark: ThemeOptions;
    };
  }
  interface ThemeOptions {
    colorSchemes?: {
      light: ThemeOptions;
      dark: ThemeOptions;
    };
  }
  interface Palette {
    complementary: Palette["primary"];
    ligth: Palette["primary"];
    dark: Palette["primary"];
  }
  interface PaletteOptions {
    complementary?: PaletteOptions["primary"];
    ligth?: PaletteOptions["primary"];
    dark?: PaletteOptions["primary"];
  }
}

const materialTheme = createTheme({
  palette: {
    complementary: {
      main: "#EE6C4D",
      dark: "#ed411A",
    },
    ligth: {
      main: "#E0FBFC",
    },
    dark: {
      main: "#293241",
    },
    primary: {
      main: "#3D5A80",
      light: "#A8D0D2",
    },
    secondary: {
      main: "#98C1D9",
    },
    success: {
      main: "#4BEBC3",
      dark: "#73B2B6"
    },
    warning: {
      main: "rgb(230, 202, 82)",
    },
    error: {
      main: "#EB613F",
    },
    background: {
      default: "#E0FBFC",
    },
  },
  typography: {
    fontFamily: [ "Dosis" ,"Handlee" ,"Ubuntu", "sans-serif"].join(","),
    h1: {
      fontFamily: "Dosis",
      fontWeight: 700,
      fontSize: "5vh",
      lineHeight: 1.5,
    },
    h2: {
      fontFamily: "Dosis",
      fontWeight: 700,
      fontSize: "50px",
      lineHeight: 1,
    },
    h3: {
      fontFamily: "Dosis",
      fontWeight: 500,
      fontSize: "35px",
      lineHeight: 1,
    },
    subtitle1: {
      fontFamily: "Dosis",
      fontWeight: 400,
      fontSize: "18px",
      lineHeight: 1.5,
    },
    subtitle2: {
      fontFamily: "Dosis",
      fontWeight: 400,
      fontSize: "16px",
      lineHeight: 1.5,
    },
    body1: {
      fontFamily: "Dosis",
      fontWeight: 400,
      fontSize: "1.1em",
      lineHeight: 1.5,
    },
    body2: {
      fontFamily: "Dosis",
      fontWeight: 400,
      fontSize: "12px",
      lineHeight: 1.5,
    },
  },
  components: {
    MuiTextField: {
      styleOverrides: {
        root: {
          borderRadius: "10px",
          borderWidth: "2px",
          borderColor: "#302E49",
          size: "small",
          "& label.Mui-focused": {
            color: "#302E49",
          },
          "& .MuiInput-underline:after": {
            borderBottomColor: "#B2BAC2",
          },
          "& .MuiInputBase-formControl-root": {
            borderColor: "#E0E3E7",
          },
          "& .MuiOutlinedInput-root": {
            "& fieldset": {
              borderColor: "#E0E3E7",
            },
            "&:hover fieldset": {
              borderColor: "#B2BAC2",
            },
            "&.Mui-focused fieldset": {
              borderColor: "#302E49",
            },
          },
        },
      },
    },
    MuiSelect: {
      styleOverrides: {
        root: {
          color: "#302E49",
          "& fieldset": {
            borderColor: "#E0E3E7",
          },
          "&:hover fieldset": {
            borderColor: "#B2BAC2",
          },
          "&.Mui-focused fieldset": {
            borderColor: "#302E49",
          },
        },
      },
    },
    MuiSnackbarContent: {
      styleOverrides: {
        root: {
          "&.notistack-MuiContent-success": {
            backgroundColor: "#4caf50", // Color para el éxito
          },
          "&.notistack-MuiContent-error": {
            backgroundColor: "#f49110", // Color para el error
          },
          "&.notistack-MuiContent-info": {
            backgroundColor: "#2196f3", // Color para la información
          },
          "&.notistack-MuiContent-warning": {
            backgroundColor: "#ff9800", // Color para la advertencia
          },
        },
      },
    },
    MuiOutlinedInput: {
      styleOverrides: {
        root: {
          color: "#302E49",
          "& fieldset": {
            borderColor: "#E0E3E7",
          },
          "&:hover fieldset": {
            borderColor: "#B2BAC2",
          },
          "&.Mui-focused fieldset": {
            borderColor: "#302E49",
          },
        },
      },
    },
  },
});

// const joyTheme = joyExtendTheme(
// 	{
// 		colorSchemes: {
// 			light: {
// 				palette: {
// 					primary: {
// 						500: "rgb(107, 92, 255)",
// 						softBg: "rgb(107, 92, 255,1)",
// 						softColor: "#",
// 						softHoverBg: "rgb(107, 92, 255,0.6)",
// 						50: "rgb(24, 30, 75)",
// 					},
// 					neutral: {
// 						500: "rgb(255, 255, 255)"
// 					},
// 					success: {
// 						500: "rgb(90, 204, 164)",
// 					},
// 					warning: {
// 						500: "rgb(230, 202, 82)",
// 					},
// 					danger: {
// 						500: "rgb(254, 101, 79)",
// 					},
// 					background: {
// 						body : "rgb(255, 255, 255)",
// 					}
// 				},
// 			},
// 		},
// 	}
// );


export { materialTheme };

