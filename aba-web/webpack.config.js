var path    = require('path');
var webpack = require('webpack');
var HtmlWebpackPlugin = require('html-webpack-plugin');
var node_modules_dir = path.join(__dirname, 'node_modules');

module.exports = {
  devtool: 'sourcemap',
  entry: {
    app: [
      './src/main/app/app.js'
    ],
    vendor: ["jquery","angular","bootstrap","angular-ui-router"]
  },
  module: {
    loaders: [
       { test: /\.js$/, exclude: [/app\/lib/, /node_modules/], loader: 'ng-annotate!babel' },
       { test: /\.html$/, loader: 'raw' },
       { test: /\.styl$/, loader: 'style!css!stylus' },
       { test: /\.less$/, loader: 'style!css!less' },
       { test: /\.css$/, loader: 'style!css' },
       { test: /lodash\.min\.js$/, loader: 'expose?_' },
       { test: /jquery\.min\.js$/, loader: 'expose?$!expose?jQuery!expose?window.jQuery' },
       { test: /angular\.min\.js/, loader: "expose?angular!exports?window.angular" },
    ]
  },
  resolve: {
    alias: {
      common: path.join(__dirname, 'src/main/app/common'),
      components: path.join(__dirname, 'src/main/app/components'),
      nodeModules: node_modules_dir,

      'jquery': path.join(node_modules_dir, 'jquery/dist/jquery.min.js'),
      'angular': path.join(node_modules_dir, 'angular/angular.min.js'),
      'angular-ui-router': path.join(node_modules_dir, 'angular-ui-router/release/angular-ui-router.min.js'),
      'bootstrap': path.join(node_modules_dir, 'bootstrap/dist/js/bootstrap.min.js'),
    }
  },
  plugins: [
    // Injects bundles in your index.html instead of wiring all manually.
    // It also adds hash to all injected assets so we don't have problems
    // with cache purging during deployment.
    new HtmlWebpackPlugin({
      template: 'src/main/index.html',
      inject: 'body',
      hash: true
    }),

    // Automatically move all modules defined outside of application directory to vendor bundle.
    // If you are using more complicated project structure, consider to specify common chunks manually.
    new webpack.optimize.CommonsChunkPlugin({
      name: 'vendor',
      minChunks: function (module, count) {
        return module.resource && module.resource.indexOf(path.resolve(__dirname, 'client')) === -1;
      }
    })
  ]
};
