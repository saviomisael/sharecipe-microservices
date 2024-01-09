import {componentWrapperDecorator, Preview} from "@storybook/angular";
import {setCompodocJson} from "@storybook/addon-docs/angular";
import docJson from "../documentation.json";

setCompodocJson(docJson);

const preview: Preview = {
  decorators: [componentWrapperDecorator((story) => `<body class="mat-typography sharecipe-theme">${story}</body>`)],
  parameters: {
    actions: {argTypesRegex: "^on[A-Z].*"},
    controls: {
      matchers: {
        color: /(background|color)$/i,
        date: /Date$/i,
      },
    },
  },
};

export default preview;
