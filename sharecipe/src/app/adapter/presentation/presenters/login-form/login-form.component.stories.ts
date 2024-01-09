import {Meta, moduleMetadata, StoryObj} from '@storybook/angular';

import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {PresentersModule} from '../presenters.module';
import {LoginFormComponent} from './login-form.component';

const meta: Meta<LoginFormComponent> = {
  component: LoginFormComponent,
  title: 'presenters/LoginFormComponent',
  decorators: [
    moduleMetadata({
      imports: [PresentersModule, BrowserAnimationsModule],
    }),
  ],
  render: (args: LoginFormComponent) => ({
    props: {
      ...args,
    },
  }),
};

export default meta;

type Story = StoryObj<LoginFormComponent>;

export const Default: Story = {
  render: () => ({
    props: {},
  }),
};
