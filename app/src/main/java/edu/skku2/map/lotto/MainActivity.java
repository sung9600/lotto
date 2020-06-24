package edu.skku2.map.lotto;

import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.util.Size;
import android.view.Surface;
import android.view.TextureView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements Camera2APIs.Camera2Interface,TextureView.SurfaceTextureListener {
    private TextureView mTextureView;
    private Camera2APIs camera2API2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextureView= findViewById(R.id.textureView);
        mTextureView.setSurfaceTextureListener(this);
        camera2API2=new Camera2APIs(this);
    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        openCamera();
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        return true;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {

    }

    @Override
    public void onCameraDeviceOpened(CameraDevice cameraDevice, Size cameraSize) {
        SurfaceTexture texture = mTextureView.getSurfaceTexture();
        texture.setDefaultBufferSize(cameraSize.getWidth(), cameraSize.getHeight());
        Surface surface = new Surface(texture);

        camera2API2.CaptureSession_4(cameraDevice, surface);
        camera2API2.CaptureRequest_5(cameraDevice, surface);
    }
    @Override
    protected void onResume() {
        super.onResume();

        if (mTextureView.isAvailable()) {
            openCamera();
        } else {
            mTextureView.setSurfaceTextureListener(this);
        }
    }
    private void openCamera() {
        CameraManager cameraManager = camera2API2.CameraManager_1(this);
        String cameraId = camera2API2.CameraCharacteristics_2(cameraManager);
        camera2API2.CameraDevice_3(cameraManager, cameraId);
    }
}





