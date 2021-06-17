import React from "react";
import { Button } from "@chakra-ui/react";
import { useClient } from "../../hooks/client";

export const UnregisterButton: React.VFC<{}> = () => {
  const client = useClient();

  return (
    <Button
      fontSize={"sm"}
      fontWeight={600}
      color={"white"}
      bg={"red.400"}
      _hover={{
        bg: "red.300",
      }}
      onClick={() =>
        window.open("https://twitter.com/settings/apps_and_sessions")
      }
    >
      登録解除
    </Button>
  );
};

export default UnregisterButton;
