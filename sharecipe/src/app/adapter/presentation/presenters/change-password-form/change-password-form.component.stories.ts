import {Meta, moduleMetadata, StoryObj} from '@storybook/angular';

import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {PresentersModule} from '../presenters.module';
import {ChangePasswordFormComponent} from './change-password-form.component';

const meta: Meta<ChangePasswordFormComponent> = {
  component: ChangePasswordFormComponent,
  title: 'presenters/ChangePasswordFormComponent',
  decorators: [
    moduleMetadata({
      imports: [PresentersModule, BrowserAnimationsModule],
    }),
  ],
  render: (args: ChangePasswordFormComponent) => ({
    props: {
      ...args,
    },
  }),
};

export default meta;

type Story = StoryObj<ChangePasswordFormComponent>;

export const Default: Story = {
  render: () => ({
    props: {},
  }),
};
