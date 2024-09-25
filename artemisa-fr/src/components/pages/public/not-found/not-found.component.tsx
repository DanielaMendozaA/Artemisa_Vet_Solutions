import { Box, Button } from "@mui/material"
import { useNavigate } from "react-router-dom"


const NotFound = () => {
  const navigate = useNavigate()
  return (
    <Box
      sx={{
        height: "100vh",
        width: "100vw",
        display: "flex",
        flexDirection: "column",
        margin: "0",
        justifyContent: "center",
        alignItems: "center",
        backgroundColor: "background.default",
      }}
    >

      <Box
        component="img"
        src="static/assets/create-a-playful-and-vibrant-404-page-illustration.jpg"
        sx={{ height: "350px", objectFit: "cover", width: "350px", borderRadius: "50%" }}
      >
      </Box>
      <Button
        onClick={() => navigate("/")}
        variant="contained"
        color="primary"
        sx={{ marginTop: "20px" }}
      >
        Go to home
      </Button>
    </Box>
  )
}

export default NotFound
