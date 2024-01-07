import {
  Meta,
  StoryObj,
  argsToTemplate,
  moduleMetadata,
} from '@storybook/angular';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { PresentersModule } from '../../presenters/presenters.module';
import { CreateAccountContainerComponent } from './create-account-container.component';

const meta: Meta<CreateAccountContainerComponent> = {
  component: CreateAccountContainerComponent,
  title: 'containers/CreateAccountContainerComponent',
  decorators: [
    moduleMetadata({
      declarations: [CreateAccountContainerComponent],
      imports: [PresentersModule, BrowserAnimationsModule],
    }),
  ],
  render: (args: CreateAccountContainerComponent) => ({
    props: {
      ...args,
    },
    template: `<app-create-account-form ${argsToTemplate(
      args
    )}></app-create-account-form>`,
  }),
};

export default meta;

type Story = StoryObj<CreateAccountContainerComponent>;

export const Default: Story = {
  render: () => ({
    props: {},
  }),
};
