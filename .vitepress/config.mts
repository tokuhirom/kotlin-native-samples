import { defineConfig } from 'vitepress'

// https://vitepress.dev/reference/site-config
export default defineConfig({
  base: '/kotlin-native-samples/',
  title: "Kotlin Native Samples",
  description: "Sample code for Kotlin/Native",
  ignoreDeadLinks: 'localhostLinks',
  themeConfig: {
    // https://vitepress.dev/reference/default-theme-config
    nav: [
      { text: 'Home', link: '/' },
    ],

    sidebar: [
      {
        text: 'Kotlin/Native とソケット',
        items: [
          { text: 'Kotlin Native で echo-server', link: '/02-sockets/01-echo-server/' },
        ]
      },
      {
        text: "Mac と Kotlin/Native",
        items: [
          { text: "Kotlin Native で ScreenCapture を使ってみる", link: "/03-mac/01-screencapturekit/" },
        ]
      }
    ],

    socialLinks: [
      { icon: 'github', link: 'https://github.com/tokuhirom/kotlin-native-samples/' }
    ]
  }
})
