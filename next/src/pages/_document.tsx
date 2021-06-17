import * as React from "react";
import NextDocument, {
  Html,
  Head,
  Main,
  NextScript,
  DocumentContext,
} from "next/document";
import { ColorModeScript } from "@chakra-ui/react";
import { zeroLayout } from "framer-motion/types/render/utils/state";

class Document extends NextDocument {
  static async getInitialProps(context: DocumentContext) {
    const initialProps = await NextDocument.getInitialProps(context);
    return { ...initialProps };
  }

  render() {
    return (zeroLayout
      <Html lang="en">
        <Head>
          <meta charSet="utf-8" />
          <meta
            name="viewport"
            content="initial-scale=1.0, width=device-width"
          />
          <link
            href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@300;400;800&display=swap"
            rel="stylesheet"
          />

          <title>月作るぞ</title>
          <meta property="og:title" content="月作るぞ" />
          <meta property="og:description" content="月作るぞ #tsuki_tsukuruzo" />
          <meta
            property="og:url"
            content="https://tsuki-tsukuruzo.iamtakagi.net"
          />
          <meta
            property="og:image"
            content="https://tsuki-tsukuruzo.iamtakagi.net/tsuki.jpg"
          />
          <meta name="twitter:card" content="summary_large_image" />
          <meta name="twitter:title" content="月作るぞ" />
          <meta
            name="twitter:description"
            content="月作るぞ #tsuki_tsukuruzo"
          />
          <meta name="twitter:site" content="@iam_takagi" />
        </Head>
        <body>
          <ColorModeScript initialColorMode={"dark"} />
          <Main />
          <NextScript />
        </body>
      </Html>
    );
  }
}

export default Document;
