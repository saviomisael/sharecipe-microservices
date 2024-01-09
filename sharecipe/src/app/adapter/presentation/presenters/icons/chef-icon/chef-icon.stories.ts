import {Meta, StoryObj} from '@storybook/angular';

import {ChefIconComponent} from './chef-icon.component';

const meta: Meta<ChefIconComponent> = {
  component: ChefIconComponent,
  title: 'presenters/ChefIconComponent',
};

export default meta;

type Story = StoryObj<ChefIconComponent>;

export const Default: Story = {
  render: () => ({
    props: {},
  }),
};
