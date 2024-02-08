import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PrestaoServicosListaComponent } from './prestao-servicos-lista.component';

describe('PrestaoServicosListaComponent', () => {
  let component: PrestaoServicosListaComponent;
  let fixture: ComponentFixture<PrestaoServicosListaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PrestaoServicosListaComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PrestaoServicosListaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
