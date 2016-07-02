var path    = require('path');
var webpack = require('webpack');
var HtmlWebpackPlugin = require('html-webpack-plugin');
var node_modules_dir = path.join(__dirname, 'node_modules');

// When you have a dep with a package.json that has a 'main' that points at a minified non-webpack friendly distribution
// try adding it to the list here (relative to node_modules). See README for more information.
// angular-locker is one such example somebody has encountered. It would look like shown below.
var deps = [
    //'angular-locker/dist/angular-locker.min.js'
];

var config = {
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
       { test: /\.(png|jpg|gif)$/, loader: "file-loader?name=images/[name].[ext]" },
       //Most libraries use semantic versioning, however kendo has some .ttf's matching 1.0 instead of 1.0.0
       { test: /\.(ttf|eot|svg)(\?v=[0-9]\.[0-9]\.[0-9])?(\?v=[0-9]\.[0-9])?$/, loader: "file-loader?name=fonts/[name].[ext]" },
       { test: /\.woff(2)?(\?v=[0-9]\.[0-9]\.[0-9])?$/, loader: "file-loader?name=fonts/[name].[ext]" },
       //kendo has some wierd ones that look like this KendoUIGlyphs.eot?-wd8xpd
       { test: /\.(ttf|eot|svg)(\?-)(.+)$/, loader: "file-loader?name=fonts/[name].[ext]" },
       { test: /\.woff(2)?(\?-)(.+)$/, loader: "file-loader?name=fonts/[name].[ext]" },
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

deps.forEach(function (dep) {
    var depPath = path.resolve(node_modules_dir, dep);
    config.resolve.alias[dep.split(path.sep)[0]] = depPath;
    config.module.noParse.push(depPath);
});

module.exports = config;
