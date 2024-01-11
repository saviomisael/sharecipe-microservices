import {Meta, moduleMetadata, StoryObj} from '@storybook/angular';

import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {PresentersModule} from '../presenters.module';
import {AlertMessageComponent} from './alert-message.component';

const meta: Meta<AlertMessageComponent> = {
  component: AlertMessageComponent,
  title: 'presenters/AlertMessageComponent',
  decorators: [
    moduleMetadata({
      imports: [PresentersModule, BrowserAnimationsModule],
    }),
  ],
  render: (args: AlertMessageComponent) => ({
    props: {
      ...args,
    },
  }),
};

export default meta;

type Story = StoryObj<AlertMessageComponent>;

export const ErrorAlert: Story = {
  render: () => ({
    props: {
      type: 'error',
      message: 'Service unavailable, try again later'
    },
  }),
};

export const SuccessAlert: Story = {
  render: () => ({
    props: {
      type: 'success',
      message: 'Password changed successfully'
    }
  })
};
