import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IndexComponent } from './index.component';
import { By } from '@angular/platform-browser';
import { ActivatedRoute } from '@angular/router';

describe('IndexComponent', () => {
  let component: IndexComponent;
  let fixture: ComponentFixture<IndexComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [IndexComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: {
            snapshot: {
              paramMap: {
                get: (key: string) => '0'
              }
            }
          }
        }
      ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(IndexComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it("should show mobile menu",()=>{
    component.isOpen = true;
    fixture.detectChanges();

    const menu = fixture.debugElement.query(By.css("#mobile"))
    
    expect(menu).toBeTruthy()
  });

  it("it should close/open the mobile menu", ()=>{
    component.isOpen = false;
    
    component.openMenu();
    fixture.detectChanges();
    const menu = fixture.debugElement.query(By.css("#mobile"));

    expect(menu).toBeTruthy()
  });
});
