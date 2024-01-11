import {Meta, moduleMetadata, StoryObj} from '@storybook/angular';

import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {PresentersModule} from '../presenters.module';
import {ChangeUsernameFormComponent} from './change-username-form.component';

const meta: Meta<ChangeUsernameFormComponent> = {
  component: ChangeUsernameFormComponent,
  title: 'presenters/ChangeUsernameFormComponent',
  decorators: [
    moduleMetadata({
      imports: [PresentersModule, BrowserAnimationsModule],
    }),
  ],
  render: (args: ChangeUsernameFormComponent) => ({
    props: {
      ...args,
    },
  }),
};

export default meta;

type Story = StoryObj<ChangeUsernameFormComponent>;

export const Default: Story = {
  render: () => ({
    props: {},
  }),
};
