package com.example.luis.gestion_viajes;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class ventana_principal extends AppCompatActivity
        implements OnNavigationItemSelectedListener,
        nueva_Basee.OnFragmentInteractionListener,
        Nueva_colonia.OnFragmentInteractionListener,
        Nueva_unidad.OnFragmentInteractionListener,
        Nuevo_operador.OnFragmentInteractionListener,
        ventana.OnFragmentInteractionListener,
        ver_unidades.OnFragmentInteractionListener,
        ver_colonias.OnFragmentInteractionListener,
        fragment_verclientes.OnFragmentInteractionListener,
        veroperadoras.OnFragmentInteractionListener,
        verViajes.OnFragmentInteractionListener

{
    private NavigationView navview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        navview = (NavigationView) findViewById(R.id.nav_view);
        navview.setNavigationItemSelectedListener(this);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        android.support.v4.app.Fragment fragment= new ventana();
        getSupportFragmentManager().beginTransaction().replace(R.id.cambio,fragment).commit();
        getSupportActionBar().setTitle("Ventana Principal");

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            //super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.ventana_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        android.support.v4.app.Fragment fragment=null;
        boolean fragmenttransaction=false;

        if (id== R.id.ventana_p) {
            fragment= new ventana();
          // fragment.getFragmentManager().beginTransaction().replace(R.id.cambio,MainScreenSubView.).
            fragmenttransaction=true;

        }else if (id == R.id.nuevabase) {

            fragment= new nueva_Basee();
            fragmenttransaction=true;
        } else if (id == R.id.nuevooperador) {

            fragment= new Nuevo_operador();
            fragmenttransaction=true;
        } else if (id == R.id.nuevacolonia) {
            fragment= new Nueva_colonia();
            fragmenttransaction=true;

        } else if (id == R.id.nuevaunidad) {
            fragment= new Nueva_unidad();
            fragmenttransaction=true;
        } else if (id == R.id.verviaje) {
            fragment= new verViajes();
            fragmenttransaction=true;

        } else if (id == R.id.vercliente) {

            fragment=new fragment_verclientes();
            fragmenttransaction=true;
        }
        else if (id==R.id.verunidades)
        {
            fragment= new ver_unidades();
            fragmenttransaction=true;
        }
        else if(id==R.id.vercolonia){
            fragment = new ver_colonias();
            fragmenttransaction=true;
        }
        else if(id==R.id.veroperadora){
            fragment = new veroperadoras();
            fragmenttransaction=true;
        }

        if (fragmenttransaction)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.cambio,fragment).commit();
            getSupportActionBar().setTitle(item.getTitle());
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
