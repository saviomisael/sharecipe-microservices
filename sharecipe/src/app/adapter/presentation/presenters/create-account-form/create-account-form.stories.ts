import { Meta, StoryObj, moduleMetadata } from '@storybook/angular';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { PresentersModule } from '../presenters.module';
import { CreateAccountFormComponent } from './create-account-form.component';

const meta: Meta<CreateAccountFormComponent> = {
  component: CreateAccountFormComponent,
  title: 'presenters/CreateAccountFormComponent',
  decorators: [
    moduleMetadata({
      imports: [PresentersModule, BrowserAnimationsModule],
    }),
  ],
  render: (args: CreateAccountFormComponent) => ({
    props: {
      ...args,
    },
  }),
};

export default meta;

type Story = StoryObj<CreateAccountFormComponent>;

export const Default: Story = {
  render: () => ({
    props: {},
  }),
};
