import {Meta, moduleMetadata, StoryObj} from '@storybook/angular';

import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {InfrastructureModule} from '../../../infrastructure/infrastructure.module';
import {ErrorsListComponent} from './errors-list.component';

const meta: Meta<ErrorsListComponent> = {
  component: ErrorsListComponent,
  title: 'presenters/ErrorsListComponent',
  decorators: [
    moduleMetadata({
      declarations: [ErrorsListComponent],
      imports: [BrowserAnimationsModule, InfrastructureModule],
    }),
  ],
  render: (args: ErrorsListComponent) => ({
    props: {
      ...args,
    },
  }),
};

export default meta;

type Story = StoryObj<ErrorsListComponent>;

export const Default: Story = {
  render: () => ({
    props: {
      errors: [
        'Username torvalds already exists.',
        'email provided is invalid.',
      ],
    },
  }),
};
