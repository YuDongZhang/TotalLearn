package com.example.totallearn.dagger_learn.register_login_7;



import dagger.Subcomponent;

/**
 * Subcomponent同时具备两种不同生命周期的scope, SubComponent具备了父Component拥有的Scope，也具备了自己的Scope。
 SubComponent的Scope范围小于父Component
 */

@Per_7Activity
@Subcomponent(modules = CModule.class)
public interface CComponent {
    void Inject(Login_7Activity activity);
}  