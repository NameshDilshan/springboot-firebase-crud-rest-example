package com.example.firebase.service;

import com.example.firebase.entity.Product;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class ProductService {
    private static final String COLLECTION_NAME = "products";

    public String saveProduct(Product product) throws ExecutionException, InterruptedException {
        Firestore dbFireStore= FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = dbFireStore.collection(COLLECTION_NAME).document(product.getName()).set(product);
        return collectionApiFuture.get().getUpdateTime().toString();
    }

    public Product getProductDetailsByName(String name) throws ExecutionException, InterruptedException {
        Firestore dbFireStore= FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFireStore.collection(COLLECTION_NAME).document(name);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        if (document.exists()){
            return document.toObject(Product.class);
        }else{
            return null;
        }
    }

    public String updateProduct(Product product) throws ExecutionException, InterruptedException {
        Firestore dbFireStore= FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = dbFireStore.collection(COLLECTION_NAME).document(product.getName()).set(product);
        return collectionApiFuture.get().getUpdateTime().toString();
    }

    public String deleteProduct(String name) throws ExecutionException, InterruptedException {
        Firestore dbFireStore= FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = dbFireStore.collection(COLLECTION_NAME).document(name).delete();
        return "Document with Product Id : "+name+ " Deleted Successfully ! ";
    }

    public List<Product> getProductDetails() throws ExecutionException, InterruptedException {
        Firestore dbFireStore= FirestoreClient.getFirestore();
        Iterable<DocumentReference> documentReference = dbFireStore.collection(COLLECTION_NAME).listDocuments();
        Iterator<DocumentReference> iterator = documentReference.iterator();

        List<Product> productList = new ArrayList<>();
        while(iterator.hasNext()){
            DocumentReference documentReference1 = iterator.next();
            ApiFuture<DocumentSnapshot> future = documentReference1.get();
            DocumentSnapshot document = future.get();
            Product product = document.toObject(Product.class);
            productList.add(product);
        }
        return productList;
    }
}
