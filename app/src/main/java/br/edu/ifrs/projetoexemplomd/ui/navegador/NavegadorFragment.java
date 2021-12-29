package br.edu.ifrs.projetoexemplomd.ui.navegador;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import br.edu.ifrs.projetoexemplomd.R;
import br.edu.ifrs.projetoexemplomd.data.Database;
import br.edu.ifrs.projetoexemplomd.ui.dica.DicaFragment;

public class NavegadorFragment extends Fragment {

    public static NavegadorFragment newInstance() {
        return new NavegadorFragment();
    }

    WebView myWebView;

    //objeto não pode estar nulo
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_navegador, container, false);
        myWebView = root.findViewById(R.id.webViewNavegador);
        abrirNavegador(Database.getNavegadorUrl());
        return root;
    }

    private void abrirNavegador(String url) {
        myWebView.setVisibility(View.VISIBLE);
        myWebView.setWebViewClient(new WebViewClient());
        myWebView.getSettings().setJavaScriptEnabled(true);
        if (url.contains(".pdf")) {
            myWebView.loadUrl("https://drive.google.com/viewerng/viewer?embedded=true&url=" + url);
        } else {
            myWebView.loadUrl(url);
        }
    }
}
