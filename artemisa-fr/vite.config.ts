import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react-swc'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [react()],
  server: {
    watch: {
      usePolling: true, // Habilitar el polling para detectar cambios en Docker
    },
    host: true, // Esto permite que el servidor se acceda desde fuera del contenedor
    strictPort: true,
    port: 5173, // Usa el mismo puerto que tienes configurado
  },
  resolve: {
    alias: {
      '@': '/src',
    }
  }
})


