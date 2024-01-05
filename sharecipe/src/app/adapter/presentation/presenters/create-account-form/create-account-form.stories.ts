import { Meta, StoryObj } from '@storybook/angular';

import { CreateAccountFormComponent } from './create-account-form.component';

const meta: Meta<CreateAccountFormComponent> = {
  component: CreateAccountFormComponent,
};

export default meta;

type Story = StoryObj<CreateAccountFormComponent>;

export const Default: Story = {
  render: () => ({
    props: {},
  }),
};
