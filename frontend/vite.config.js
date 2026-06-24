import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'
import fs from 'fs'
import path from 'path'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [react()],
  server: {
    port: 3000,
    // HTTPS using mkcert-generated trusted local certificate.
    // The CA is installed in Windows Trusted Root - no browser warnings.
    https: {
      key: fs.readFileSync(path.resolve(__dirname, 'ssl/localhost.key')),
      cert: fs.readFileSync(path.resolve(__dirname, 'ssl/localhost.crt')),
    },
    proxy: {
      '/api': {
        target: 'http://localhost:8080',
        changeOrigin: true,
        secure: false
      }
    }
  }
})
