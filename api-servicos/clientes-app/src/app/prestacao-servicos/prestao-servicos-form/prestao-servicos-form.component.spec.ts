import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PrestaoServicosFormComponent } from './prestao-servicos-form.component';

describe('PrestaoServicosFormComponent', () => {
  let component: PrestaoServicosFormComponent;
  let fixture: ComponentFixture<PrestaoServicosFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PrestaoServicosFormComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PrestaoServicosFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
