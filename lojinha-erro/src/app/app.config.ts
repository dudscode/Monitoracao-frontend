import { ApplicationConfig, ErrorHandler, provideZoneChangeDetection } from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
import { ErrorMonitoringService } from './core/error/error-monitoring.service';

export const appConfig: ApplicationConfig = {
  providers: [provideZoneChangeDetection({ eventCoalescing: true }), provideRouter(routes),
    {
      provide: ErrorHandler, useClass: ErrorMonitoringService
    }
  ]
};
