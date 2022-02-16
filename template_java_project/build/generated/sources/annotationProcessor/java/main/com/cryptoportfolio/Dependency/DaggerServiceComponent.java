package com.cryptoportfolio.Dependency;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.cryptoportfolio.activity.CreatePortfolioActivity;
import com.cryptoportfolio.activity.GetPortfolioActivity;
import com.cryptoportfolio.activity.LoginActivity;
import com.cryptoportfolio.activity.RegisterActivity;
import com.cryptoportfolio.activity.UpdatePortfolioActivity;
import com.cryptoportfolio.activity.VerifyActivity;
import com.cryptoportfolio.dynamodb.dao.AssetDao;
import com.cryptoportfolio.dynamodb.dao.PortfolioDao;
import com.cryptoportfolio.dynamodb.dao.UserDao;
import com.cryptoportfolio.lambda.CoinGeckoConnector;
import com.google.gson.Gson;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class DaggerServiceComponent implements ServiceComponent {
  private Provider<DynamoDBMapper> provideDynamoDBMapperProvider;


  private Provider<Gson> provideGsonProvider;

  private DaggerServiceComponent(Builder builder) {
    initialize(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public static ServiceComponent create() {
    return new Builder().build();
  }

  private PortfolioDao getPortfolioDao() {
    return new PortfolioDao(provideDynamoDBMapperProvider.get());
  }

  private AssetDao getAssetDao() {
    return new AssetDao(provideDynamoDBMapperProvider.get());
  }

  @SuppressWarnings("unchecked")
  private void initialize(final Builder builder) {
    this.provideDynamoDBMapperProvider =
        DoubleCheck.provider(DaoModule_ProvideDynamoDBMapperFactory.create(builder.daoModule));
    this.provideGsonProvider =
        DoubleCheck.provider(DaoModule_ProvideGsonFactory.create(builder.daoModule));
  }

  @Override
  public CreatePortfolioActivity provideCreatePortfolioActivity() {
    return new CreatePortfolioActivity(getPortfolioDao(), getAssetDao(), provideGsonProvider.get());
  }

  @Override
  public GetPortfolioActivity provideGetPortfolioActivity() {
    return new GetPortfolioActivity(getPortfolioDao(), getAssetDao(), provideGsonProvider.get());
  }

  @Override
  public LoginActivity provideLoginActivity() {
    return new LoginActivity(new UserDao(), provideGsonProvider.get());
  }

  @Override
  public RegisterActivity provideRegisterActivity() {
    return new RegisterActivity(new UserDao(), provideGsonProvider.get());
  }

  @Override
  public UpdatePortfolioActivity provideUpdatePortfolioActivity() {
    return new UpdatePortfolioActivity(getPortfolioDao(), getAssetDao(), provideGsonProvider.get());
  }

  @Override
  public VerifyActivity provideVerifyActivity() {
    return new VerifyActivity(new UserDao(), provideGsonProvider.get());
  }

  @Override
  public CoinGeckoConnector provideCoinGeckoConnector() {
    return new CoinGeckoConnector(getAssetDao(), provideGsonProvider.get());
  }

  public static final class Builder {
    private DaoModule daoModule;

    private Builder() {}

    public ServiceComponent build() {
      if (daoModule == null) {
        this.daoModule = new DaoModule();
      }
      return new DaggerServiceComponent(this);
    }

    public Builder daoModule(DaoModule daoModule) {
      this.daoModule = Preconditions.checkNotNull(daoModule);
      return this;
    }
  }
}
