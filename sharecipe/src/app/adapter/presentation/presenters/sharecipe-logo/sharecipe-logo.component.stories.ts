import {argsToTemplate, Meta, moduleMetadata, StoryObj,} from '@storybook/angular';

import {SharecipeLogoComponent} from './sharecipe-logo.component';

const meta: Meta<SharecipeLogoComponent> = {
  component: SharecipeLogoComponent,
  title: 'presenters/SharecipeLogoComponent',
  decorators: [
    moduleMetadata({
      declarations: [SharecipeLogoComponent],
    }),
  ],
  render: (args: SharecipeLogoComponent) => ({
    props: {
      ...args,
    },
    template: `<app-create-account-form ${argsToTemplate(
      args
    )}></app-create-account-form>`,
  }),
};

export default meta;

type Story = StoryObj<SharecipeLogoComponent>;

export const Default: Story = {
  render: () => ({
    props: {},
  }),
};
